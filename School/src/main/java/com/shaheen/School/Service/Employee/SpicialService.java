/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee;

import com.shaheen.School.Model.Employee.Spicial;
import java.util.List;

/**
 *
 * @author lts
 */
public interface SpicialService {
        public List<Spicial> findAll();

    public Spicial save(Spicial spicial);

    public boolean delete(Spicial spicial);

    public Spicial findOne(Long id);
}
