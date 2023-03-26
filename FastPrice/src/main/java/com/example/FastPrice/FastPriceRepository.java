package com.example.FastPrice;

import org.springframework.data.repository.CrudRepository;

public interface FastPriceRepository extends CrudRepository<FastPrice, Long> {
//here Simply creating repository and extends to crud to do all operation
}
