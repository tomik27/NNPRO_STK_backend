package cz.upce.nnpro_stk_backend.config;

import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.mail.EmailDetails;
import cz.upce.nnpro_stk_backend.services.CarService;
import cz.upce.nnpro_stk_backend.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ScheduledConfig {
        @Autowired
        private CarService carService;
        @Autowired
        private ModelMapper mapper;

        private final EmailService emailService;

        public ScheduledConfig(EmailService emailService) {
                this.emailService = emailService;
        }
/*
        @Scheduled(cron = "0 0 21,22 * * *")
        public void invokeScheduled() {
                List<Car> allCars = carService.getAllCars();
                for (int i = 0; i < allCars.size(); i++)
                {
                        Car car = allCars.get(i);
                        LocalDate expiryDateOfSTK = car.getExpiryDateOfSTK();
                        //LocalDate currentLocalDate= LocalDate.now();
                        LocalDate tempDateTime = LocalDate.from( LocalDate.now() );

                        long days = tempDateTime.until( expiryDateOfSTK, ChronoUnit.DAYS );
                        tempDateTime = tempDateTime.plusDays( days );
                        System.out.println("Days "+days);
                         if(days<=30)
                                sendMail(car);
                }
        }*/

        private void sendMail(Car car){
                //todo get emailBySPZ from CRV
                String SPZ=car.getSpz();
                LocalDate localDate = car.getExpiryDateOfSTK();
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient("tstomikac@gmail.com");
                emailDetails.setSubject("Upozornění na končící STK");
                String hello="Vážený zákazníku,";
                String introduction="dovolujeme si Vás upozornit, že za 30 dní Vám končí STK vašeho auta. SPZ:.";
                String findUS="Prosíme, navštivte nás, co nejdříve. Adresa STK: Technická 131, 583 01 Pardubice 3";
                String  generate="Tento mail byl vygenerován. Neodpovídejte na něj.";
                String bye="Děkujeme,"+"\n"+" Vaše STK Pardubice."+"\n"+"Tel. číslo: 123456789.";
                emailDetails.setMsgBody(hello+ "\n"+introduction+"\n\n" +findUS+ "\n\n"+generate+"\n\n"+bye );

                String s = emailService.sendSimpleMail(emailDetails);
                System.out.println(s);
        }


    }
