package App.BusinessLayer.Controllers;

import App.BusinessLayer.Pojo.FavoriteDirectionPOJO;
import App.BusinessLayer.Pojo.NotificationPOJO;
import App.BusinessLayer.Pojo.VehiclePOJO;
import App.BusinessLayer.Services.UserService;
import App.BusinessLayer.Services.VehicleService;
import App.DataLayer.Models.FavoriteDirectionModel;
import App.DataLayer.Models.NotificationModel;
import App.DataLayer.Models.UserModel;
import App.DataLayer.Models.VehicleModel;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cesar
 */

@RestController
// RequestMapping atiende las peticiones en la ruta dada por parametro
@RequestMapping("/api/vehicle")
// CrossOrigin permite el acceso desde paginas web diferentes a localhost
// Por ser entorno de pruebas se le da acceso a cualquier pagina web externa
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,
        RequestMethod.DELETE, RequestMethod.PUT})

public class VehicleController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    // Autowired asigna un objeto a la instancia en el momento en el que
    // sea requerido

    @Autowired
    private VehicleService vehicleService;

    private UserService userService;

    public VehicleController(VehicleService vehicleService,
                             UserService userService) {
        this.vehicleService = vehicleService;
        this.userService = userService;
    }
/*
    public VehicleModel fillModel(VehiclePOJO vehiclePOJO,int idUser) {
        VehicleModel vehicle = new VehicleModel();
        vehicle.setVehicleOwner(idUser);
        vehicle.setVehicleLicenseplate(vehiclePOJO.getVehicleLicenseplate());
        vehicle.setVehicleType(vehiclePOJO.getVehicleType());
        vehicle.setVehicleModel(vehiclePOJO.getVehicleModel());
        vehicle.setVehicleYear(vehiclePOJO.getVehicleYear());
        vehicle.setVehicleColor(vehiclePOJO.getVehicleColor());
        vehicle.setVehicleRegistryDatetime(vehiclePOJO
        .getVehicleRegistryDatetime());
        vehicle.setVehiclePicture(vehiclePOJO.getVehiclePicture());
        vehicle.setVehicleCapacity(vehiclePOJO.getVehicleCapacity());
        vehicle.setVehicleBrand(vehiclePOJO.getVehicleBrand());
        vehicle.setVehicleServiceType(vehiclePOJO.getVehicleServiceType());
        vehicle.setVehicleBody(vehiclePOJO.getVehicleBody());
        vehicle.setVehicleSoatExpiration(vehiclePOJO.getVehicleSoatExpiration
        ());
        vehicle.setVehicleEngine(vehiclePOJO.getVehicleEngine());
        vehicle.setVehicleGasType(vehiclePOJO.getVehicleGasType());
        return vehicle;
    }

    public VehiclePOJO fillPojo(VehicleModel vehicleModel) {
        VehiclePOJO vehicle = new VehiclePOJO();
        vehicle.setVehicleOwner(vehicleModel.getVehicleOwner());
        vehicle.setVehicleLicenseplate(vehicleModel.getVehicleLicenseplate());
        vehicle.setVehicleType(vehicleModel.getVehicleType());
        vehicle.setVehicleModel(vehicleModel.getVehicleModel());
        vehicle.setVehicleYear(vehicleModel.getVehicleYear());
        vehicle.setVehicleColor(vehicleModel.getVehicleColor());
        vehicle.setVehicleRegistryDatetime(vehicleModel
        .getVehicleRegistryDatetime());
        vehicle.setVehiclePicture(vehicleModel.getVehiclePicture());
        vehicle.setVehicleCapacity(vehicleModel.getVehicleCapacity());
        vehicle.setVehicleBrand(vehicleModel.getVehicleBrand());
        vehicle.setVehicleServiceType(vehicleModel.getVehicleServiceType());
        vehicle.setVehicleBody(vehicleModel.getVehicleBody());
        vehicle.setVehicleSoatExpiration(vehicleModel
        .getVehicleSoatExpiration());
        vehicle.setVehicleEngine(vehicleModel.getVehicleEngine());
        vehicle.setVehicleGasType(vehicleModel.getVehicleGasType());
        return vehicle;
    }*/

    // GetMapping obtiene valores en una sub ruta dada como parametro
    @GetMapping
    public List<VehiclePOJO> findAll() {
        List<VehiclePOJO> vehiclePOJOS = new ArrayList<>();
        List<VehicleModel> vehicleModels = vehicleService.findAll();
        for (VehicleModel vehicleModel : vehicleModels) {
            vehiclePOJOS.add(new VehiclePOJO(vehicleModel));
        }
        return vehiclePOJOS;
    }

    // GetMapping obtiene valores en una sub ruta dada como parametro
    @GetMapping("/{id}")
    public ResponseEntity<VehiclePOJO> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(new VehiclePOJO(vehicleService.findById(id)));
        } catch (JsonParseException e) {
            logger.error(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            logger.error(HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    /*// PostMapping hace una peticion post a la ruta del controlador
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody VehiclePOJO vehiclePOJO) {
        try {
            vehicleService.save(fillModel(vehiclePOJO));
            logger.trace(HttpStatus.CREATED.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/

    // PutMapping hace una peticion put a la ruta del controlador
    /*@PutMapping("delete-vehicle/{id}")
    public ResponseEntity<Void> update(@RequestBody VehiclePOJO vehiclePOJO) {
        try {
            // Busqueda de prueba para saber si el registro ya existe
            VehicleModel vehicleModell =
                    vehicleService.findById(vehiclePOJO.getIdVehicle());
            vehicleService.save(fillModel(vehiclePOJO));
            logger.trace(HttpStatus.CREATED.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (JsonParseException e) {
            logger.error(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            logger.error(HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }*/

    @PostMapping(value = {"/new-vehicle"})
    public ResponseEntity<Void> addVehicleToUser(@RequestBody VehiclePOJO vehiclePOJO) {

        try {
            String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
            UserModel user = userService.findByUserMail(email);
            vehiclePOJO.setVehicleOwner(user.getIdUser());
            vehicleService.save(vehiclePOJO.getModel(user.getIdUser()));
            logger.trace(HttpStatus.CREATED.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = {"/update-vehicle"})
    public ResponseEntity<Void> updateVehicleToUser(@RequestBody VehiclePOJO vehiclePOJO) {

        try {
            String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
            UserModel user = userService.findByUserMail(email);
            vehiclePOJO.setVehicleOwner(user.getIdUser());
            VehicleModel vehicleModel = vehicleService.getVehicleByvehicleOwner(user.getIdUser());

            String imgSelected = vehiclePOJO.getVehiclePicture();

            if (imgSelected == null) imgSelected = "";

            if (!imgSelected.equals("")){

                String fileName = email + "vehicle" + ".txt";

                String os = System.getProperty("os.name");

                if (os.equals("Windows 10")){

                    File namePath = new File(".");
                    String namePathDone = namePath.getAbsoluteFile().getParentFile().getCanonicalPath();

                    String picRoute = namePathDone + "\\pictures\\vehicles\\" + fileName;
                    File myObj = new File(picRoute);
                    FileWriter wrt = new FileWriter(picRoute);
                    wrt.write(imgSelected);
                    wrt.close();
                    vehiclePOJO.setVehiclePicture(picRoute);

                }else{
                    File namePath = new File(".");
                    String namePathDone = "/home/engdiazmu/WheelsUS-Backend/pictures/vehicles/";
                    String picRoute = namePathDone + fileName;
                    File myObj = new File(picRoute);
                    FileWriter wrt = new FileWriter(picRoute);
                    wrt.write(imgSelected);
                    wrt.close();
                    vehiclePOJO.setVehiclePicture(picRoute);
                }

            }

            if (vehicleModel == null){
                vehicleService.save(vehiclePOJO.getModel(user.getIdUser()));
            }else {
                vehiclePOJO.setIdVehicle(vehicleModel.getIdVehicle());
                vehicleService.save(vehiclePOJO.getModel(user.getIdUser()));
            }

            logger.trace(HttpStatus.CREATED.toString());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/show-vehicles")
    public ResponseEntity<VehiclePOJO> getVehicleByUser() {

        try {
            String email = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
            UserModel user = userService.findByUserMail( email );
            VehicleModel vehicleModel = vehicleService.getVehicleByvehicleOwner(user.getIdUser());

            String vehicleImg = vehicleModel.getVehiclePicture();

            if (vehicleImg == null) vehicleImg = "";

            if(!vehicleImg.equals("")){

                String data = "";
                String os = System.getProperty("os.name");

                if (os.equals("Windows 10")){

                    File namePath = new File(".");
                    String namePathDone = namePath.getAbsoluteFile().getParentFile().getCanonicalPath();


                    String picAddres = namePathDone + "\\pictures\\vehicles\\" + email + "vehicle" + ".txt";
                    File base64 = new File(picAddres);
                    Scanner myReader = new Scanner(base64);

                    while (myReader.hasNextLine()) {
                        data = myReader.nextLine();
                    }
                    myReader.close();
                    vehicleModel.setVehiclePicture(data);

                }else{
                    File namePath = new File(".");
                    String namePathDone = "/home/engdiazmu/WheelsUS-Backend/pictures/vehicles/";


                    String picAddres = namePathDone + email + "vehicle" + ".txt";
                    File base64 = new File(picAddres);
                    Scanner myReader = new Scanner(base64);
                    while (myReader.hasNextLine()) {
                        data = myReader.nextLine();
                    }
                    myReader.close();

                    vehicleModel.setVehiclePicture(data);
                }
            }

            return ResponseEntity.ok(new VehiclePOJO(vehicleModel));

        } catch (JsonParseException e) {
            logger.error(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            logger.error(HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }
    }

    // DeleteMapping hace una peticion delete a la ruta del controlador
    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleModel> deleteById(@PathVariable int id) {

        try {
            vehicleService.deleteById(id);
            logger.trace(HttpStatus.OK.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            logger.error(HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JsonParseException e) {
            logger.error(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}