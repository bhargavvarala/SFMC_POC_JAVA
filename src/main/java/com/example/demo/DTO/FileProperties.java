package com.example.demo.DTO;

import java.io.Serializable;

public class FileProperties implements Serializable{
String fileName;

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

@Override
public String toString() {
	return "FileProperties [fileName=" + fileName + "]";
}

}
