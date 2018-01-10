package com.evil.base;

import java.awt.Component;
import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileChoose {	
	public static void select(ChooseCallback callback){
		JFileChooser fileChooser = new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView(); // ע���ˣ�������Ҫ��һ��
		Map<String,String[]> filters = callback.setExtensionFilter();
		Set<String> set = filters.keySet();
		for (String key : set) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(key,filters.get(key));
			fileChooser.addChoosableFileFilter(filter);
		}
		
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle(callback.setChooseTitle());
		fileChooser.setApproveButtonText("ȷ��");
		
		fileChooser.setFileSelectionMode(callback.setFileSelectionMode());
		
		int result = fileChooser.showOpenDialog(callback.attachParent());
		
		if (JFileChooser.APPROVE_OPTION == result) {
			callback.chooseResult(fileChooser.getSelectedFile());
		}
	}
	
	public interface ChooseCallback{
		/**
		 * ���ù�����
		 * @return {"text"{"txt,xml,java"},"java"{"java"}}
		 */
		Map<String,String[]> setExtensionFilter();
		
		/**
		 * ���ñ���
		 * @return 
		 */
		String setChooseTitle();
		/**
		 * @return �����Ĵ���
		 */
		Component attachParent();
		/**
		 * ����ص�
		 * @param file
		 */
		void chooseResult(File file);
		/**
		 * ����ѡ��ģʽ
		 * @return JFileChooser.FILES_AND_DIRECTORIES ��
		 */
		int setFileSelectionMode();
	}
}
