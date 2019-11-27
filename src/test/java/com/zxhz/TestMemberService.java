package com.zxhz;
import javax.annotation.Resource;
import com.zxhz.service.MemberServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ShipperApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMemberService {
	@Resource
	private MemberServiceImpl memberService ;
	@Test
	public void testGet() {
		System.out.println(this.memberService.findById("admin"));
	}
	@Test
	public void testAuth() {
		System.out.println(this.memberService.listAuthByMember("admin"));
	}
	@Test
	public void testWord() {


	}

}