/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.School.Service.Employee.Impl;

import com.shaheen.School.Model.Employee.Spicial;
import com.shaheen.School.Repository.Employee.SpicialRepository;
import com.shaheen.School.Service.Employee.SpicialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lts
 */
@Service
public class SpicialServiceImpl implements SpicialService {

    @Autowired
    SpicialRepository spicialRepository;

    @Override
    public List<Spicial> findAll() {

        return spicialRepository.findAll();
    }

    @Override
    public Spicial save(Spicial spicial) {

        return spicialRepository.save(spicial);
    }

    @Override
    public boolean delete(Spicial spicial) {
        Spicial spicialTofind = findOne(spicial.getId());
        if (spicialTofind.getSpicialTitle() != null && !spicialTofind.getSpicialTitle().equals("")) {
            spicialRepository.delete(spicialTofind);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Spicial findOne(Long id) {
        return spicialRepository.getOne(id);

    }

}
