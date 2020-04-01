package com.coins;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coins.entity.rbac.Sections;
import com.coins.mapper.rbac.SectionsMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionTest {
	@Autowired
	private SectionsMapper sectionMapper;
	
	@Test
	public void testSelect()
	{
		List<Sections> list = sectionMapper.selectList(null);
		System.out.println("fdsa");
		list.forEach(System.out::println);
	}
}
