package com.QACK.Web.Model;

import java.io.Serializable;
import java.util.List;

import com.QACK.Web.util.QACKUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "tb_permission")
public class Permission extends QACKUtil implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String description;
    private Boolean userManager;
    private Boolean create;
    private Boolean update;
    private Boolean delete;
    private Boolean exportReport;
    private Boolean comment;
    
    @OneToMany(mappedBy = "permission")
    private List<Role> roles;
    
	public Permission() {}

	public Permission(Integer id, String description, Boolean userManager, Boolean create, Boolean update, Boolean delete,
			Boolean exportReport, Boolean comment) {
		this.id = id;
		this.description = description;
		this.userManager = userManager;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.exportReport = exportReport;
		this.comment = comment;
	}

	public void updateData( Permission obj ) {
		obj.verifyData();
		this.description = obj.getDescription();
		this.userManager = obj.getUserManager();
		this.create = obj.getCreate();
		this.update = obj.getUpdate();
		this.delete = obj.getDelete();
		this.exportReport = obj.getExportReport();
		this.comment = obj.getComment();
	}
	
	@Override
	public List<Object> attributesToList(){
		try {			
			return List.of(this.description,
					this.userManager,
					this.create,
					this.update,
					this.delete,
					this.exportReport,
					this.comment);
		}catch( NullPointerException e){
			return null;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getUserManager() {
		return userManager;
	}

	public void setUserManager(Boolean userManager) {
		this.userManager = userManager;
	}

	public Boolean getCreate() {
		return create;
	}

	public void setCreate(Boolean create) {
		this.create = create;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getExportReport() {
		return exportReport;
	}

	public void setExportReport(Boolean exportReport) {
		this.exportReport = exportReport;
	}

	public Boolean getComment() {
		return comment;
	}

	public void setComment(Boolean comment) {
		this.comment = comment;
	}
}
