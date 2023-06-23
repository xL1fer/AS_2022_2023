## NetBeans Apache Kafka libs installation

After having kafka extracted somewhere in the system, open Netbeans and select "**Tools > Libraries**" to open the Library Manager.

Click "**New Library...**" and type the library name ("Apache Kafka" for example).

Finally, click "**Add JAR/Folder...**" button and search for the "libs" folder of your installation, for instance: 

	"C:\kafka_2.13-3.1.0\libs\"
	
Select all .jar files inside the "**libs**" folder.

To add the Kafka library to the project, in the "**Projects**" left menu right click "**Libraries > Add Library...**" and choose the created library.

> (https://help.accusoft.com/ImageGear-Java/v2.0/Windows/HTML/topic31.html)