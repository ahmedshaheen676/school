/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.DdFlie;

import com.shaheen.School.Model.Common.DBFile;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lts
 */
public interface DBfileService {
    public DBFile storeFile(MultipartFile file);
     public DBFile getFile(long fileId) throws Throwable ;
}
