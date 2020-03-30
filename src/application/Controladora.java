package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controladora {

	@FXML
	private Button botonguardar;
	@FXML
	private Button botonborrar;
	@FXML
	private Button botonmodificar;
	@FXML
	private Button botoneliminar;
	@FXML
	private TextField nombreText;
	@FXML
	private TextField apellidoText;
	@FXML
	private TextField emailText;
	@FXML
	RadioButton hombreRadioB = new RadioButton ("H");
	@FXML
	RadioButton mujerRadioB = new RadioButton ("M");
	@FXML
	ToggleGroup sexo;
	@FXML
	private CheckBox soltero;	
	@FXML
	private TableView<Persona> tabla;
	@FXML
	private TableColumn<Persona, String> nombreColum;
	@FXML
	private TableColumn<Persona, String> apellidoColum;
	@FXML
	private TableColumn<Persona, String> emailColum;
	@FXML
	private TableColumn<Persona, Character> sexoColum;
	@FXML
	private TableColumn<Persona, Boolean> solteroColum;
	
	private final ObservableList<Persona> datos =  FXCollections.observableArrayList();
	
    public void initialize() {

    	tabla.setItems(this.datos);
    	
    	nombreColum.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
    	apellidoColum.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellido"));
    	emailColum.setCellValueFactory(new PropertyValueFactory<Persona,String>("email"));
    	sexoColum.setCellValueFactory(new PropertyValueFactory<Persona,Character>("sexo"));
    	solteroColum.setCellValueFactory(new PropertyValueFactory<Persona,Boolean>("soltero"));
    	
    }
    
    public void guardarDatos(ActionEvent event){
    	
    	String nombre2 = this.nombreText.getText();
        String apellido2 = this.apellidoText.getText();
        String email2 = this.emailText.getText();
        Character sexo = 'z';
        Boolean soltero = false;

        if (this.hombreRadioB.isSelected()==true){
        	sexo ='H';
        }else {
			sexo='M';
		}
        if (this.soltero.isSelected()==true){ 
        	soltero = true;
        }

        Persona nueva = new Persona (nombre2,apellido2,email2,sexo,soltero);
    	this.nombreText.setText("");
    	this.apellidoText.setText("");
    	this.emailText.setText("");
    	this.soltero.setSelected(false);
    	this.hombreRadioB.setSelected(false);
    	this.mujerRadioB.setSelected(false);
    	
        if(this.tabla.getItems().contains(nueva)==true){
			Alert alert = new Alert (Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La persona existe");
			alert.showAndWait();
        }else {
            this.datos.add(nueva);
            this.tabla.setItems(this.datos);
		}

    }
    public void modificarDatos (ActionEvent event){
    	
    	Persona m = this.tabla.getSelectionModel().getSelectedItem();
    	
    	if (m == null){
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setHeaderText(null);
    		alert.setTitle("Error");
    		alert.setContentText("Debes seleccionar una persona.");
    		alert.showAndWait();
    	}else {
    		
        	String nombre2 = this.nombreText.getText();
            String apellido2 = this.apellidoText.getText();
            String email2 = this.emailText.getText();
            Character sexo = 'z';
            Boolean soltero = false;

            if (this.hombreRadioB.isSelected()==true){
            	sexo ='H';
            }else {
    			sexo='M';
    		}
            if (this.soltero.isSelected()==true){ 
            	soltero = true;
            }

            Persona aux = new Persona (nombre2,apellido2,email2,sexo,soltero);
            
            if (this.datos.contains(aux)==false){
            	m.setNombre(aux.getNombre());
            	m.setApellido(aux.getApellido());
            	m.setEmail(aux.getEmail());
            	m.setSexo(aux.getSexo());    	
            	m.setSoltero(soltero);
            	this.tabla.refresh();
            }
		}
    }
    public void borrarDatos (ActionEvent event){
    	
    	this.nombreText.setText("");
    	this.apellidoText.setText("");
    	this.emailText.setText("");
    	this.soltero.setSelected(false);
    	this.hombreRadioB.setSelected(false);
    	this.mujerRadioB.setSelected(false);
    }
    public void eliminarFila (ActionEvent event){
		int index = tabla.getSelectionModel().getSelectedIndex();
		System.out.println(index);
		if( index >= 0){
			Persona seleccionada = tabla.getSelectionModel().getSelectedItem();	
			this.datos.remove(seleccionada);
			tabla.setItems(this.datos);
			this.tabla.refresh();
	    	this.nombreText.setText("");
	    	this.apellidoText.setText("");
	    	this.emailText.setText("");
	    	this.soltero.setSelected(false);
	    	this.hombreRadioB.setSelected(false);
	    	this.mujerRadioB.setSelected(false);
		}else{

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en selección de datos");
			alert.setContentText("NO HAY NINGUN ELEMENTO SELECCIONADO!");
			alert.showAndWait();

		}
    }
}
