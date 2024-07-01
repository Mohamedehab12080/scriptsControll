package com.scripts.controll.dashboard.scriptsController.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripts.controll.dashboard.scriptsController.model.cokies;
import com.scripts.controll.dashboard.scriptsController.repository.cokiesRepository;

@Service
public class cokiesServiceImpl implements cokiesServiceInterface{
	
	@Autowired
	private cokiesRepository cokiesRepo;

	@Override
	public Optional<cokies> findById(Long id) {
		return cokiesRepo.findById(id);
	}

	@Override
	public cokies findByOrderLimit1() {
		cokies returned =null;
		if(cokiesRepo.findFirstByOrderByIdDesc().isPresent())
		{
			returned=cokiesRepo.findFirstByOrderByIdDesc().get();
		}
			return returned ;	
		
	}

	@Override
	public String deleteById(Long id) {
		Optional<cokies> finded=findById(id);
		if(!finded.isPresent())
		{
			return "can't be deleted";
		}else 
		{
			cokiesRepo.deleteById(id);
			return "deleted";
		}
	}

	@Override
	public cokies findByName(String cokiesName) {
		return cokiesRepo.findByCokies(cokiesName);
	}

	@Override
	public String insert(cokies cokies) {
		cokies inserted= cokiesRepo.save(cokies);
		if(inserted!=null)
		{
			return "inserted";
		}else 
		{
			return "can't be insert";
		}
	}

	@Override
	public List<String> insertAll(List<cokies> cokiesList) {
		List<cokies> insertedCokies=cokiesRepo.saveAll(cokiesList);
		List<String> insertedNames=new ArrayList<String>();
		if(!insertedCokies.isEmpty())
		{
			for(cokies insertedCokie:insertedCokies)
			{
				insertedNames.add(insertedCokie.getCokies());
			}
		}
		return insertedNames;
	}

	@Override
	public List<cokies> findAll() {
		return cokiesRepo.findAll();
	}

	@Override
	public void truncateTable() {
		cokiesRepo.truncateTable();
	}
	


}
