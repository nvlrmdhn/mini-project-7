package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.Merchandise;
import id.co.indivara.jdt12.miniprojecttest2.repo.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchandiseController {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @PostMapping("/create/merchandise")
    public Merchandise createMerchandise(@RequestBody Merchandise merchandise){
        Merchandise ms = new Merchandise();
        ms.setMerchandiseId("mrc"+(merchandiseRepository.count()+1));
        ms.setMerchandiseName(merchandise.getMerchandiseName());
        return merchandiseRepository.save(ms);
    }

    @GetMapping("/find/merchandise/{merchandiseId}")
    public Merchandise findById(@PathVariable String merchandiseId){
        return merchandiseRepository.findById(merchandiseId).get();
    }

    @GetMapping("/find/merchandise/all")
    public List<Merchandise> findAll(){
        return merchandiseRepository.findAll();
    }

    @DeleteMapping("/delete/merchandise/{merchendiseId}")
    public void deleteMerchandise(@PathVariable String merchandiseId){
        merchandiseRepository.deleteById(merchandiseId);
    }



}
