package com.apress.ravi.chapter6.SpringTesting;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@WebAppConfiguration
@ContextConfiguration
public class SampleTest {

}
