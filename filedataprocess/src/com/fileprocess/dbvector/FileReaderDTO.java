package com.fileprocess.dbvector;

public class FileReaderDTO {
	private int srNo;
	private String firstNm;
	private String lastNm;
	private String birthDate;
	private String deathDate;
	private int loanAmnt;
	
	public FileReaderDTO(int srNo, String firstNm, String lastNm, String birthDate, String deathDate,
			int loanAmnt) {
		this.srNo = srNo;
		this.firstNm = firstNm;
		this.lastNm = lastNm;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.loanAmnt = loanAmnt;
	}
	
	public FileReaderDTO(){
		super();
	}
	
	public int getSrNo() {
		return srNo;
	}
	public String getFirstNm() {
		return firstNm;
	}
	public String getLastNm() {
		return lastNm;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	
	public int getLoanAmnt() {
		return loanAmnt;
	}
	public void setLoanAmnt(int loanAmnt) {
		this.loanAmnt = loanAmnt;
	}
	public String toString(){
		return ("Sr No. : " + this.getSrNo() +" | First Name : "+this.getFirstNm()
				+ " | Last Name : "+this.getLastNm()+" | Date of Birth : "+this.getBirthDate()
				+ " | Death Date : "+this.getDeathDate()+" | Loan Amount : " + this.getLoanAmnt());
	}
}
