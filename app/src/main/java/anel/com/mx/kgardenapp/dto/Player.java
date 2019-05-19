package anel.com.mx.kgardenapp.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase ayuda a manejar la obtencion y presistencia de los datos
 *
 */

public class Player {

    /**
     *
     * @param id
     * @param name
     * @param age
     * @param gender
     * @param mail
     * @param img
     * @param registered
     */
    public Player(String id, String name, Long age, String gender, String mail, String img, Boolean registered) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.img = img;
        this.registered = registered;
    }

    public Player(String id, String name, Long age, String gender){

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //se sobreescribe constructor Default
    public Player(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public List<Actividad> getActividadList() {
        return ActividadList;
    }

    public void setActividadList(Actividad actividad) {
        ActividadList.add(actividad);
    }




    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    /*estos fields deben de coincidir con los definidos en firebase*/
    private String id;
    private String name;
    private Long age;
    private String gender;
    private String mail;
    private String img;
    private Boolean registered;
    private  Actividad actividad;
    private List<Actividad> ActividadList = new ArrayList<Actividad>();


}
