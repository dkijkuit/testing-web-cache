package org.cache.testing.webcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingWebCacheApplication {

	public static void main(String[] args) {
		/*for (int i=0; i<20; i++) {
			System.out.println("private String stringItem"+i+";");
		}
		for (int i=0; i<20; i++) {
			System.out.println("private int intItem"+i+";");
		}
		for (int i=0; i<20; i++) {
			System.out.println("private double doubleItem"+i+";");
		}
		for (int i=0; i<20; i++) {
			System.out.println("private UUID uuidItem"+i+";");
		}*/

/*
		for (int i=0; i<20; i++) {
			System.out.println("cacheItem.setStringItem"+i+"(\"stringItem"+i+"\");");
		}
		for (int i=0; i<20; i++) {
			System.out.println("cacheItem.setDoubleItem"+i+"("+i+");");
		}
		for (int i=0; i<20; i++) {
			System.out.println("cacheItem.setIntItem"+i+"("+i+");");
		}
		for (int i=0; i<20; i++) {
			System.out.println("cacheItem.setUuidItem"+i+"(UUID.randomUUID());");
		}
*/
		/*for (int i=0; i<20; i++) {
			System.out.println("stringItem"+i+" = in.readString();");
		}
		for (int i=0; i<20; i++) {
			System.out.println("intItem"+i+" = in.readInt();");
		}
		for (int i=0; i<20; i++) {
			System.out.println("doubleItem"+i+" = in.readDouble();");
		}
		for (int i=0; i<20; i++) {
			System.out.println("uuidItem"+i+" = UUID.fromString(in.readString())");
		}*/

		/*for (int i=0; i<20; i++) {
			System.out.println("out.writeString(stringItem"+i+");");
		}
		for (int i=0; i<20; i++) {
			System.out.println("out.writeInt(intItem"+i+");");
		}
		for (int i=0; i<20; i++) {
			System.out.println("out.writeDouble(doubleItem"+i+");");
		}
		for (int i=0; i<20; i++) {
			System.out.println("out.writeString(uuidItem"+i+".toString());");
		}*/

		SpringApplication.run(TestingWebCacheApplication.class, args);
	}

}
