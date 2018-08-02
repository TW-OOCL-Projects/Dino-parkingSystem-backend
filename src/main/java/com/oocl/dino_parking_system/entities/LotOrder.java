package com.oocl.dino_parking_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.ZonedDateTime;

import static com.oocl.dino_parking_system.constants.Constants.STATUS_NOHANDLE;
import static com.oocl.dino_parking_system.constants.Constants.TYPE_PARKCAR;

@Table(name = "car_order")
@Entity
public class LotOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @CreatedDate
    private ZonedDateTime parkingDate;

    @ManyToOne
    @JoinColumn(name = "parking_boy_id")
    @JsonIgnore
    private User parkingBoy;

    private String plateNumber;
    private String status;
    private String receiptId;

    public LotOrder(String type, String plateNumber, String status, String receiptId) {
        this.type = type;
        this.plateNumber = plateNumber;
        this.status = status;
        this.receiptId = receiptId;
    }

    public LotOrder(String plateNumber, String receiptId) {
        this.plateNumber = plateNumber;
        this.receiptId = receiptId;
        this.status = STATUS_NOHANDLE;
        this.type = TYPE_PARKCAR;
    }

    public LotOrder(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(User parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

	public ZonedDateTime getParkingDate() {
		return parkingDate;
	}

	public void setParkingDate(ZonedDateTime parkingDate) {
		this.parkingDate = parkingDate;
	}
}
