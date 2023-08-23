package vn.tholv.web.core.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.tholv.web.core.base.entity.core.BaseEntity;
import vn.tholv.web.core.base.service.core.Service;

import java.util.List;

public class AbstractController<T extends BaseEntity<T, ID>, ID> {

	@Autowired
	private Service<T, ID> service;

	@GetMapping("/{id}")
	public T findById(@PathVariable String id) throws Exception{
		return this.service.findById(id);
	}

	@PostMapping("/save")
	public T save(@RequestBody T entity) {
		return this.service.save(entity);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) throws Exception{
		this.service.deleteById(id);
	}

	@PutMapping("/update")
	public T update(@RequestBody T entity) throws Exception{
		return this.service.update(entity);
	}

	@PostMapping("/priority")
	public List<T> prioritySort(@RequestBody List<T> entityList) {
		return this.service.priority(entityList);
	}

	@GetMapping("/findAll")
	public List<T> findAll() {return this.service.findAll();}

	@PostMapping("/findAllByCondition")
	public List<T> findAll(@RequestBody T t) {
		return this.service.findAllByCondition(t);
	}

	@PostMapping("/findPageByCondition")
	public Page<T> findPageByCondition(@RequestBody T t, @RequestParam int page, @RequestParam int size) {
		return this.service.findPageByCondition(t, page, size);
	}

	@PostMapping("/findPage/{page}/{size}")
	public Page<T> findPage(@PathVariable int page, @PathVariable int size) {
		return this.service.findPage(page, size);
	}
}
