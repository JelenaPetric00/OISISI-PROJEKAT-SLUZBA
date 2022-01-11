package controller;

import model.DBRemainingSubjects;
import model.Subject;
import view.tabs.RemainingSubjectsTab;

public class RemainingSubjectsCtrl {
	
	RemainingSubjectsTab remainingSubjectsTab;
	DBRemainingSubjects dbRemainingSubjects;
	
	private static RemainingSubjectsCtrl instance = null;
	
	public static RemainingSubjectsCtrl getInstance(){
		if(instance == null){
			instance = new RemainingSubjectsCtrl();
		}
		return instance;
	}
	
	private RemainingSubjectsCtrl(){
		remainingSubjectsTab = RemainingSubjectsTab.getInstance(null);
		dbRemainingSubjects = DBRemainingSubjects.getInstance();
	}
	
	public Subject getRemainingSubjectsAtIdx(int i){
		return DBRemainingSubjects.getInstance().getRemainingSubjects().get(i);
	}
	

}
