package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import entity.model.Payment;
import entity.model.Student;
import exception.PaymentValidationException;
import exception.StudentNotFoundException;

public interface PaymentDao {
	public Student getStudent(Payment payment) throws StudentNotFoundException, FileNotFoundException, ClassNotFoundException, IOException;
	 public double getPaymentAmount(Payment payment) throws PaymentValidationException, FileNotFoundException, ClassNotFoundException, IOException;
	 public Date getPaymentDate(Payment payment) throws PaymentValidationException, FileNotFoundException, ClassNotFoundException, IOException;
}
