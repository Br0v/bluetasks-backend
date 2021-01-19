package br.com.softblue.bluetasks.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.softblue.bluetasks.domain.user.AppUser;

@Entity
@EntityListeners(TaskListener.class)//para conectar o usuario logado a uma tarefa
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "A descrição é obrigatória")
	@Length(min = 3, max = 60, message = "O tamanho da tarefa é inválido")
	private String description;
	
	@NotNull(message = "A data da tarefa é obrigatória")
	@FutureOrPresent(message = "A data da tarefa não pode estar no passado")
	private LocalDate whenToDo;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	@JsonIgnore
	private AppUser appUser;
	
	private Boolean done = false;
	
	public Task() {
		
	}
	
	

	public Task(String description, LocalDate whenToDo, Boolean done) {
		super();
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getWhenToDo() {
		return whenToDo;
	}

	public void setWhenToDo(LocalDate whenToDo) {
		this.whenToDo = whenToDo;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Integer getId() {
		return id;
	}



	public AppUser getAppUser() {
		return appUser;
	}



	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
}
