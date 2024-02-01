package com.flow.extension.domain.extension.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flow.extension.domain.extension.entity.Extension;

@Mapper
@Repository
public interface ExtensionRepository {

	List<Extension> findAll();

	void insertExtension(Extension extension);

	void deleteExtension(Extension extension);
}
