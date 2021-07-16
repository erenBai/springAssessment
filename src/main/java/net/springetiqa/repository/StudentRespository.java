package net.springetiqa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springetiqa.entity.Student;

@Repository
public interface StudentRespository extends JpaRepository<Student,Long>{
	List<Student> findByName(String name);

}
