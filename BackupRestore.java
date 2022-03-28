import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BackupRestore {

	public static void main(String[] args) {
		
		//Change this pdf to whatever you want to backup/restore
		System.out.println(restore("syllabus.pdf", 3));

	}
	public static int backup(String filename, double partSize) {
		int count=1;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
				int numFiles = (int)(in.available()/(partSize*1024*1024)+1);
				for(int i=1; i<=numFiles; i++) {
					File f = new File(filename+count);
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
					for(int k=0; k<(partSize*1024*1024) && in.available()>0; k++) {
						out.write(in.read());
					}
					count++;
				}
			return numFiles;
		}catch(IOException e) {
			System.out.println("Error");
			return -1;
		}
	}
	
	public static int restore(String filename, int numberOfPieces) {
		int size=0, read, count=0;
		File g = new File(filename);
		try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(g));) {
			for(int i=1; i<=numberOfPieces; i++) {
				File f = new File(filename+i);
				try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));){
					read=in.available();
				
					while(read > 0) {
						out.write(in.read());
						count++;
						read=in.available();
					}
				}
			}
			return count;
		}catch(IOException e){
			System.out.println("Error");
			return -1;
			
		}
	}
	
	

}
