/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.DdFlie.Impl;

import com.shaheen.School.Exceptions.FileStorageException;
import com.shaheen.School.Exceptions.MyFileNotFoundException;
import com.shaheen.School.Model.Common.DBFile;
import com.shaheen.School.Repository.DbFile.DBFileRepository;
import com.shaheen.School.Service.DdFlie.DBfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class DBServiceImpl implements DBfileService {

    @Autowired
    private DBFileRepository dbFileRepository;

    @Override
    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public DBFile getFile(long fileId) throws Throwable {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
