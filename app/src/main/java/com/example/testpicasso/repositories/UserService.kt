package com.example.testpicasso.repositories
import com.example.testpicasso.AIRPLANE
import com.example.testpicasso.BUS
import com.example.testpicasso.FERRY
import com.example.testpicasso.TRAIN
import com.example.testpicasso.data.TourPackage
import com.example.testpicasso.data.TransportType
import com.example.testpicasso.data.User
import java.time.LocalDateTime

object UserService {
    private var currentUser : User? = null
    private var currentPackage : TourPackage? = null
    private var currentPackageDetail : TourPackage? = null

    fun getCurrentUser() : User? {
        return  currentUser
    }

    fun setCurrentUser(currentUser: User?){
        this.currentUser = currentUser
    }
    fun getCurrentPackage() : TourPackage? {
        return  currentPackage
    }

    fun setCurrentPackage(currentPackage : TourPackage?){
        this.currentPackage = currentPackage
    }

    fun getCurrentPackageDetail() : TourPackage? {
        return  currentPackageDetail
    }

    fun setCurrentPackageDetail(currentPackage : TourPackage?){
        this.currentPackageDetail = currentPackage
    }


    fun calculateAmountToPayForThePackage(currentPackage: TourPackage): Pair<Double, Double>  {
        val transportType : TransportType = currentPackage.transport
        val currentDateAndTime : LocalDateTime = LocalDateTime.now()
        var totalAmountToPay = 0.0
        var commissionTotal = 0.0
        when(transportType){
            TransportType.BUS -> {
                val bus = BUS(currentPackage.price,currentDateAndTime)
                totalAmountToPay = bus.calculateAmountToPay()
                commissionTotal = bus.getCommission()
            }
            TransportType.TRAIN -> {
                val train = TRAIN(currentPackage.price,currentDateAndTime)
                totalAmountToPay = train.calculateAmountToPay()
                commissionTotal = train.getCommission()
            }
            TransportType.AIRPLANE -> {
                val airplane = AIRPLANE(currentPackage.price,currentDateAndTime)
                totalAmountToPay = airplane.calculateAmountToPay()
                commissionTotal = airplane.getCommission()
            }
            TransportType.FERRY -> {
                val ferry = FERRY(currentPackage.price,currentDateAndTime)
                totalAmountToPay = ferry.calculateAmountToPay()
                commissionTotal = ferry.getCommission()
            }
        }
        return Pair(totalAmountToPay , commissionTotal)
    }


}