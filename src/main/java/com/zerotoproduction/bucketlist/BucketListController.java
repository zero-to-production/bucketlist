package com.zerotoproduction.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class BucketListController {

    @Autowired
    BucketRepository bucketRepository;

    @GetMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok(bucketRepository.findAll());
    }

    @GetMapping(value = "/bucket")
    public ResponseEntity getBucket(@RequestParam(value="id") Long id) {
        Optional<BucketList> foundBucketList = bucketRepository.findById(id);

        if(foundBucketList.isPresent()){
            return ResponseEntity.ok(foundBucketList.get());
        }else {
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity addToBucketList(@RequestParam(value="name") String name, @RequestParam(value="description") String desc) {
        return ResponseEntity.ok(bucketRepository.save(new BucketList(name, desc)));
    }

    @PutMapping(value = "/")
    public ResponseEntity updateBucketList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id, @RequestParam(value="description") String desc) {
        Optional<BucketList> optionalBucketList = bucketRepository.findById(id);
        if(!optionalBucketList.isPresent()){
            return ResponseEntity.badRequest().body("No bucket with specified id " + id + " found");
        }

        BucketList foundBucketList = optionalBucketList.get();
        foundBucketList.setName(name);
        foundBucketList.setDescription(desc);

        return ResponseEntity.ok(bucketRepository.save(foundBucketList));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity removeBucketList(@RequestParam(value="id") Long id) {
        bucketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
