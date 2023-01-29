package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.AssetType;
import com.example.demo.DTO.Category;
import com.example.demo.DTO.CreatedBy;
import com.example.demo.DTO.FolderResponseDTO;
import com.example.demo.DTO.Html;
import com.example.demo.DTO.Items;
import com.example.demo.DTO.ModifiedBy;
import com.example.demo.DTO.Preheader;
import com.example.demo.DTO.SharingProperties;
import com.example.demo.DTO.Subjectline;
import com.example.demo.DTO.TemplateRequestDTO;
import com.example.demo.DTO.TemplateResponseDTO;
import com.example.demo.DTO.Views;
import com.example.demo.controller.AccessTokenRequest;
import com.example.demo.controller.AccessTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SfmcPocService {

	@Autowired
    private RestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	public  ResponseEntity<AccessTokenResponse> getToken(String sfmcBuId, boolean isSharedFolder) {
		AccessTokenRequest  accessTokenRequest = new AccessTokenRequest();
		if(isSharedFolder)
			accessTokenRequest.setAccount_id("1406642");
		else
			accessTokenRequest.setAccount_id(sfmcBuId);
		accessTokenRequest.setClient_id("whn3dekghtag2aeyvjps37vj");
		accessTokenRequest.setClient_secret("eKvHneog0ncTGzOrJqmS7B2M");
		accessTokenRequest.setGrant_type("client_credentials");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccessTokenRequest> request = new HttpEntity<>(accessTokenRequest, headers);
        
        
		ResponseEntity<AccessTokenResponse> result = this.restTemplate.postForEntity("https://mchv3f7y086x57pyhstqdm8yvp9m.auth.marketingcloudapis.com/v2/token", 
									request, AccessTokenResponse.class);

		return result;
		
	}

	public String postTemplateToLocation(int templateId, String sfmcBuId, boolean isSharedFolder) throws IOException, GitAPIException {
		File directory33 = new File("/Users/bbetikunti/Documents/SFMCtoGIT/");
		File file33 = null;
		ResponseEntity<AccessTokenResponse> tokenResponse = getToken(sfmcBuId, isSharedFolder);
		//System.out.println("Access token for "+sfmcBuId+" - "+tokenResponse.getBody().getAccess_token());
		if(!tokenResponse.getStatusCode().is2xxSuccessful()) {
			return "Error creating access token";
			
		}
		//fetch template from SFMC 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ tokenResponse.getBody().getAccess_token());
        HttpEntity <String> entity = new HttpEntity <> (headers);
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("assetId", templateId);
        // API call to sfmc to get template
        ResponseEntity <TemplateResponseDTO> response = restTemplate.exchange("https://mchv3f7y086x57pyhstqdm8yvp9m.rest.marketingcloudapis.com/asset/v1/content/assets/{assetId}", HttpMethod.GET, entity, TemplateResponseDTO.class,uriVariables);
        //Cloning the repository
        Git gitdec = Git.cloneRepository().setURI("https://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@github.com/bhargavvarala/SFMC-TO-API.git")
    			.setDirectory(directory33).setCredentialsProvider(new UsernamePasswordCredentialsProvider("bhargavvarala", "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"))
    			.call();
        // storing template into file to get version  
        
        file33 = new File(directory33.getAbsolutePath()+"/"+templateId+".json");
    	System.out.println( "File created "+file33.createNewFile() +"with file name "+ file33.getName()+" at path"+file33.getAbsolutePath());
        //writeObjectToFile(response.getBody(), file33); // writing to file
    	writeObjectToFileJson(response.getBody(), file33);
  	  	gitdec.add().addFilepattern(".").call();
  	  	gitdec.commit().setMessage( "Commited "+ response.getBody().getId()).call();
  	  	gitdec.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider("bhargavvarala", "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")).setPushAll()
				.setForce(true).call();
  	  	System.out.println("Response bdy-- "+response.getBody().toString());
  	  	FileUtils.deleteDirectory(directory33);
  	  	System.out.println("Directory deleted to reduce memory usage");
  	  	return "File pushed to git with id  "+ response.getBody().getId();
       
	}
	

	private void writeObjectToFileJson(TemplateResponseDTO body, File file33) {
		ObjectMapper om = new ObjectMapper();
		try {
			om.writerWithDefaultPrettyPrinter().writeValue(file33, body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeObjectToFile(TemplateResponseDTO obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }
	
	
	public String postTemplatetoSFMC(int templateId, String sfmcBuId, boolean isSharedFolder, String status, int catogeryID) throws ClassNotFoundException, IOException, InvalidRemoteException, TransportException, GitAPIException {
		
		File directory44 = new File("/Users/bbetikunti/Documents/GITtoSFMC/");
		ResponseEntity<TemplateRequestDTO> response  = null;
		//clone fresh repo from 
		Git gitdec = Git.cloneRepository().setURI("https://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@github.com/bhargavvarala/SFMC-TO-API.git")
    			.setDirectory(directory44).setCredentialsProvider(new UsernamePasswordCredentialsProvider("bhargavvarala", "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"))
    			.call();
		Path path = Paths.get("/Users/bbetikunti/Documents/GITtoSFMC/"+templateId+".json");
		if(!Files.exists(path))
			return "template with id: "+templateId +" dosen't exists";
		File file44 = new File(directory44.getAbsolutePath()+"/"+templateId+".json");
		//TemplateResponseDTO templatefromFile = readObjectFromFile(file44); // reading from file
		TemplateResponseDTO templatefromFile = new ObjectMapper().readerFor(TemplateResponseDTO.class).readValue(file44);
		System.out.println("ID from file "+templatefromFile.getId()+"created by"+ templatefromFile.getCreatedBy().getName()+" Modified by "+templatefromFile.getModifiedBy().getName());
		
		ResponseEntity<AccessTokenResponse> tokenResponse = getToken(sfmcBuId, isSharedFolder);
		System.out.println("Access token for "+sfmcBuId+" - "+tokenResponse.getBody().getAccess_token());
		if(!tokenResponse.getStatusCode().is2xxSuccessful()) {
			return "Error creating access token";
			
		}
		TemplateRequestDTO templateRequestDTO = new TemplateRequestDTO();
		configureTemplateObject(templateRequestDTO, templatefromFile,isSharedFolder, catogeryID);
		
		
		System.out.println("Catogery---"+templateRequestDTO.getCategory().toString()) ;

		//check if the destination BU contains folder structure
		String destinationBuId = templatefromFile.getMemberId().toString();
		//String sourceFolderName = templatefromFile.getCategory().getName();
		//checkIfDestinationBuContainsFolder(destinationBuId, sourceFolderName);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ tokenResponse.getBody().getAccess_token());
        HttpEntity <?> entity = new HttpEntity <> (templateRequestDTO, headers);
        System.out.println("Request body to sfmc - "+mapper.writeValueAsString(templateRequestDTO));
        if(status.equals("save") ) {
            response = restTemplate.exchange("https://mchv3f7y086x57pyhstqdm8yvp9m.rest.marketingcloudapis.com/asset/v1/content/assets", HttpMethod.POST, entity, TemplateRequestDTO.class);

        }
        else
        {
//        	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//            converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
//            converter.setObjectMapper(mapper);
//            
//        	HttpClient httpClient = HttpClients.createDefault();
//            RestTemplate restTemplate2 = new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));
//            restTemplate2.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient)); 
//           
//            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.PATCH, new HttpEntity<EmailPatch>(patch),
//                            String.class);
            response = restTemplate.exchange("https://mchv3f7y086x57pyhstqdm8yvp9m.rest.marketingcloudapis.com/asset/v1/content/assets/"+templateId, HttpMethod.PATCH, entity, TemplateRequestDTO.class);
            
        }
        FileUtils.deleteDirectory(directory44);
  	  	System.out.println("Directory deleted to reduce memory usage");
		return "created with name - "+ response.getBody().getName()+" in "+ templateRequestDTO.getCategory().toString();
	}
	
	private void configureTemplateObject(TemplateRequestDTO templateRequestDTO, TemplateResponseDTO templatefromFile, boolean isSharedFolder, int catogeryID) {
		
		templateRequestDTO.setName(templatefromFile.getName()); 

		Views views = new Views();
		Html html = new Html();
		html.setContent(templatefromFile.getViews().getHtml().getContent());
		views.setHtml(html);
		
		Subjectline subjectLine = new Subjectline();
		subjectLine.setContent(templatefromFile.getViews().getSubjectline().getContent());
		views.setSubjectline(subjectLine );
		
		templateRequestDTO.setViews(views);
		
		AssetType assetType = new AssetType();
		assetType.setId(templatefromFile.getAssetType().getId());
		assetType.setName(templatefromFile.getAssetType().getName());
		assetType.setDisplayName(templatefromFile.getAssetType().getDisplayName());
		templateRequestDTO.setAssetType(assetType );
		
		SharingProperties sharingProperties  = new SharingProperties();
		sharingProperties.setSharedWith(templatefromFile.getSharingProperties().getSharedWith());
		sharingProperties.setSharingType(templatefromFile.getSharingProperties().getSharingType());
		templateRequestDTO.setSharingProperties(sharingProperties);
		
		Category category = new Category();
		category.setId(catogeryID);
		
		templateRequestDTO.setCategory(category);
		
		templateRequestDTO.setOwner(templatefromFile.getOwner());
		
		CreatedBy createdBy = new CreatedBy();
		createdBy.setEmail(templatefromFile.getCreatedBy().getEmail());
		createdBy.setId(templatefromFile.getCreatedBy().getId());
		createdBy.setName(templatefromFile.getCreatedBy().getName());
		createdBy.setUserId(templatefromFile.getCreatedBy().getUserId());
		templateRequestDTO.setCreatedBy(createdBy );
		
		ModifiedBy modifiedBy = new ModifiedBy();
		modifiedBy.setEmail(templatefromFile.getModifiedBy().getEmail());
		modifiedBy.setId(templatefromFile.getModifiedBy().getId());
		modifiedBy.setName(templatefromFile.getModifiedBy().getName());
		modifiedBy.setUserId(templatefromFile.getModifiedBy().getUserId());
		templateRequestDTO.setModifiedBy(modifiedBy );
		
	}

	private void checkIfDestinationBuContainsFolder(String destinationBuId, String sourceFolderName) {
		ResponseEntity<AccessTokenResponse> tokenResponse = getToken(destinationBuId,false);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ tokenResponse.getBody().getAccess_token());
        HttpEntity <String> entity = new HttpEntity <> (headers);
        System.out.println("Folders");
        ResponseEntity <FolderResponseDTO> response = restTemplate.exchange("https://mchv3f7y086x57pyhstqdm8yvp9m.rest.marketingcloudapis.com/asset/v1/content/categories", HttpMethod.GET, entity, FolderResponseDTO.class);
        List<Items> item =  response.getBody().getItems().stream().filter(a -> a.getName().equals(sourceFolderName)).collect(Collectors.toList());
        //if()
        //item.get(0)
		//System.out.println(r);
		
	}

	public static TemplateResponseDTO readObjectFromFile(File file) throws IOException, ClassNotFoundException {
		TemplateResponseDTO result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (TemplateResponseDTO) ois.readObject();
        }
       
        return result;
    }

	

	
}


































