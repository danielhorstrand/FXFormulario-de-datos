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
	
	//atributos para codificar la edicion
	private boolean edicion;
	private int indiceedicion;
	
    public void initialize() {

    	tabla.setItems(this.datos);
    	
    	nombreColum.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
    	apellidoColum.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellido"));
    	emailColum.setCellValueFactory(new PropertyValueFactory<Persona,String>("email"));
    	sexoColum.setCellValueFactory(new PropertyValueFactory<Persona,Character>("sexo"));
    	solteroColum.setCellValueFactory(new PropertyValueFactory<Persona,Boolean>("soltero"));
    	
    	edicion = false;
    	indiceedicion = 0;
    }
    
    public void guardarDatos(){
    	
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
    	
        if (edicion==true){
        	Persona editada = datos.get(indiceedicion);
        	editada.setNombre(this.nombreText.getText());
        	editada.setApellido(this.apellidoText.getText());
        	editada.setEmail(this.emailText.getText());
       		editada.setSoltero(soltero);
       		editada.setSexo(sexo);
       		datos.set(indiceedicion, editada);
       	}else {
               this.datos.add(nueva);
               this.tabla.setItems(this.datos);
			}
    }
    public void modificarDatos (){
    	
    	int index = this.tabla.getSelectionModel().getSelectedIndex();

    	if (index>=0){
    		
        	edicion = true;
        	indiceedicion = index;
        	
    		Persona m = this.tabla.getSelectionModel().getSelectedItem();
    		this.nombreText.setText(m.getNombre());
    		this.apellidoText.setText(m.getApellido());
    		this.emailText.setText(m.getEmail());
    		if(m.getSexo()=='M'){
    			this.mujerRadioB.setSelected(true);
    		}else {
    			this.hombreRadioB.setSelected(true);
			}
    		if(m.isSoltero()==true){
    			this.soltero.setSelected(true);
    		}else {
				this.soltero.setSelected(false);
			}
    	}
        	
    }
    public void borrarDatos (){
    	
    	this.nombreText.setText("");
    	this.apellidoText.setText("");
    	this.emailText.setText("");
    	this.soltero.setSelected(false);
    	this.hombreRadioB.setSelected(false);
    	this.mujerRadioB.setSelected(false);
    	edicion = false;
    	indiceedicion = 0;
    }
    public void eliminarFila (){
		int index = tabla.getSelectionModel().getSelectedIndex();
		System.out.println(index);
		if( index >= 0){
			Persona seleccionada = tabla.getSelectionModel().getSelectedItem();	
			this.datos.remove(seleccionada);
			tabla.setItems(this.datos);
			this.tabla.refresh();
			borrarDatos();
	    	edicion = false;
	    	indiceedicion = 0;
		}else{

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en selección de datos");
			alert.setContentText("NO HAY NINGUN ELEMENTO SELECCIONADO!");
			alert.showAndWait();

		}
    }
}
