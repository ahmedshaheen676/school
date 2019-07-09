/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Repository.DbFile;

import com.shaheen.School.Model.Common.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lts
 */
public interface DBFileRepository extends JpaRepository<DBFile, Long>{
    
}
