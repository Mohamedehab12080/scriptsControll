package com.scripts.controll.dashboard.scriptsController.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scripts.controll.dashboard.scriptsController.model.Number;
import com.scripts.controll.dashboard.scriptsController.repository.numberRepository;

import jakarta.transaction.Transactional;

@Service
public class numberServiceImpl implements numberServiceInterface{

	
	@Autowired
	private numberRepository numberRepo;
	
	@Override
	public String deleteById(Long id) {
		Optional<Number> finded=findById(id);
		if(!finded.isPresent())
		{
			return "can't be deleted";
		}else 
		{
			numberRepo.deleteById(id);
			return "deleted";
		}
	}

	@Override
	public String insert(Number number) {
		Number inserted= numberRepo.save(number);
		if(inserted!=null)
		{
			return "inserted";
		}else 
		{
			return "can't be insert";
		}
	}

	@Override
	public List<Number> findAll() {
		return numberRepo.findAll();
	}

	@Override
	public List<String> insertAll(List<Number> numbersList) {
		List<Number> insertedNumbers=numberRepo.saveAll(numbersList);
		List<String> insertedNames=new ArrayList<String>();
		if(!insertedNumbers.isEmpty())
		{
			for(Number insertedNumber:insertedNumbers)
			{
				insertedNames.add(insertedNumber.getNumber());
			}
		}
		return insertedNames;
	}

	@Override
	public Optional<Number> findById(Long id) {
		return numberRepo.findById(id);
	}

	@Override
	public Number findByName(String numberName) {
		return numberRepo.findByNumber(numberName);
	}

	@Override
	public void truncateTable() {
		numberRepo.truncateTable();
	}

	@Override
	public Number findByOrderLimit1() {
		Number returned =null;
		if(numberRepo.findFirstByOrderByIdDesc().isPresent())
		{
			returned=numberRepo.findFirstByOrderByIdDesc().get();
		}
			return returned ;
	}

}
