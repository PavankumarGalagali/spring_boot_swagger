package com.te.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.springboot.bean.EmployeeBean;
import com.te.springboot.exp.EmployeeExp;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public EmployeeBean getEmployee(int id) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
		EntityManager entityManager = factory.createEntityManager();

		EmployeeBean employeeBean = entityManager.find(EmployeeBean.class, id);
		return employeeBean;

	}

	@Override
	public boolean deleteEmpData(int id) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			EmployeeBean bean = entityManager.find(EmployeeBean.class, id);
			if (bean != null) {
				entityManager.remove(bean);
				transaction.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public List<EmployeeBean> getAllEmp() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
		EntityManager entityManager = factory.createEntityManager();

		String query = " from EmployeeBean ";
		Query query2 = entityManager.createQuery(query);

		List<EmployeeBean> allEmpData = query2.getResultList();

		return allEmpData;

	}

	@Override
	public boolean addEmployee(EmployeeBean bean) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		boolean isAdded = false;
		try {
			transaction.begin();
			entityManager.persist(bean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			transaction.rollback();
			throw new EmployeeExp("Data is Already Present");
		}
		return isAdded;
	}

	@Override
	public boolean updateEmployee(EmployeeBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isUpdated = false;

		try {
			transaction.begin();
			EmployeeBean info = manager.find(EmployeeBean.class, bean.getId());
			if (bean.getName() != null && bean.getName() != "") {
				info.setName(bean.getName());
			}
			if (bean.getBirthDate() != null) {
				info.setBirthDate(bean.getBirthDate());
			}

			if (bean.getPassword() != null && bean.getPassword() != "") {
				info.setPassword(bean.getPassword());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return isUpdated;
	}

}
