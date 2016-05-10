# DramaScript
An existing framework for android encapsulation and simplify the development of libraries

#Support for network, a new way of architecture, a high degree of decomposition, powerful logging tools, such as your project development is no longer so difficult, welcome to download.
If you are using the Android Studio, then add the following:

allprojects {
		repositories {
			...
			maven { url "https://www.jitpack.io" }
		}
	}
	
	dependencies {
	        compile 'com.github.DramaScript:DramaScript:1.0'
	}
	If you is eclipse, then add the following maven:
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://www.jitpack.io</url>
		</repository>
	</repositories>
	Step 2. Add the dependency
	<dependency>
	    <groupId>com.github.DramaScript</groupId>
	    <artifactId>DramaScript</artifactId>
	    <version>1.0</version>
	</dependency>
