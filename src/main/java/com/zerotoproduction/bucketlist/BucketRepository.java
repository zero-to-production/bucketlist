package com.zerotoproduction.bucketlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketList, Long> {
}
