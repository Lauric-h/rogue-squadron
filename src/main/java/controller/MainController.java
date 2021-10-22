package controller;

import dao.VehicleDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import model.Type;
import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class MainController {
    // GET /api/vehicles -> return all vehicles
    // GET /api/vehicles/{id} -> return 1 vehicle
    // GET /api/vehicles?type={type} -> return all vehicle of type {type}

    @Autowired
    VehicleDao vehicleDao;

    @Operation(summary = "Get all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vehicles",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "/api/vehicles")
    public MappingJacksonValue getAllVehicles() {
        Iterable<Vehicle> vehicles = vehicleDao.findAll();
        return new MappingJacksonValue(vehicles);
    }

    @GetMapping(value = "/api/vehicles")
    public List<Vehicle> getAllVehiclesByType(@RequestParam(required = false) String paramType) {
        for (Type vehicleType : Type.values()){
           if (Objects.equals(vehicleType.getDisplayValue(), paramType)) {
               return vehicleDao.findByType(vehicleType);
           }
        }
        return vehicleDao.findAll();
    }

    @Operation(summary = "Get vehicle by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vehicle",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Persona not found",
                    content = @Content)})
    @GetMapping(value = "/api/vehicles/{id}")
    public Vehicle getVehicleById(@PathVariable int id) {
        return vehicleDao.findById(id);
    }





}
