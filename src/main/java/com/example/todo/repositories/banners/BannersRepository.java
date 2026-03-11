package com.example.todo.repositories.banners;

import com.example.todo.entities.banners.Banners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannersRepository extends JpaRepository<Banners, Long> {
}
