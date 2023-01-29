package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TemplateInfo;
import com.example.demo.service.SfmcPocService;

@RestController
@RequestMapping(path = "/sfmc-poc")
public class SfmcPocController {

	
	@Autowired
	private SfmcPocService sfmcPocService;
	
	

	@PostMapping("save-template-to-git")
	public String postTemplateToLocation(@RequestBody TemplateInfo templateInfo) throws IOException, ClassNotFoundException, GitAPIException {
		
        return sfmcPocService.postTemplateToLocation(templateInfo.getTemplateId(), templateInfo.getSfmcBuId(), templateInfo.isSharedFolder());
	}
	

	@PostMapping("save-template-to-SFMC")	
	public String postTemplatetoSFMC(@RequestBody TemplateInfo templateInfo) throws IOException, ClassNotFoundException, InvalidRemoteException, TransportException, GitAPIException {
		
        return sfmcPocService.postTemplatetoSFMC(templateInfo.getTemplateId(), templateInfo.getSfmcBuId(), templateInfo.isSharedFolder(), "save",templateInfo.getCategoryID());
	}

	@PatchMapping("update-template-to-SFMC")	
	public String updateTemplatetoSFMC(@RequestBody TemplateInfo templateInfo) throws IOException, ClassNotFoundException, InvalidRemoteException, TransportException, GitAPIException {
		
        return sfmcPocService.postTemplatetoSFMC(templateInfo.getTemplateId(), templateInfo.getSfmcBuId(), templateInfo.isSharedFolder(),"update", templateInfo.getCategoryID());
	}
	@GetMapping("test")	
	public String test(@RequestParam int templateId) throws IOException, ClassNotFoundException, InvalidRemoteException, TransportException, GitAPIException {
		System.out.println("1");
		File directory44 = new File("/Users/bbetikunti/Documents/GITtoSFMC/");
		File file44 = null;
		Git gitdec = Git.cloneRepository().setURI("https://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@github.com/bhargavvarala/SFMC-TO-API.git")
    			.setDirectory(directory44).setCredentialsProvider(new UsernamePasswordCredentialsProvider("bhargavvarala", "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"))
    			.call();
		System.out.println("22");
		Path path = Paths.get("/Users/bbetikunti/Documents/GITtoSFMC/"+templateId+".txt");
		System.out.println("3 ----"+ Files.exists(path));

		boolean exists = Files.exists(path); 
		System.out.println("4");

		System.out.println("file exists "+ exists);
		Path pathq = Paths.get(directory44.getAbsolutePath());
		System.out.println("Deleting "+ directory44.getAbsolutePath());
//		Files.walk(path)
//	    .sorted(Comparator.reverseOrder())
//	    .map(Path::toFile)
//	    .forEach(File::delete);
		FileUtils.deleteDirectory(directory44);
		System.out.println("deeleted ----");
		
		return "deleted";
	}
	
	
	
              
	
}
