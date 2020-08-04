package co.demo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import co.demo.config.HibernateConfig;

public class DataAccess {

	public static <T> int save(final T obj) {
		int newId = 0;
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			try {
				newId = (int) session.save(obj);
				transaction.commit();
			} catch (Exception e) {
				newId = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return newId;
	}

	public static int delete(final Object obj) {
		int newId = 0;
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
			newId = 1;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return newId;
	}

	public static <T> int update(final T obj) {
		int newId = 0;
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
			newId = 1;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return newId;
	}

	public static <T> int saveOrUpdate(final T obj) {
		int newId = 0;
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(obj);
			transaction.commit();
			newId = 1;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return newId;
	}

	public static <T> T get(Class<T> c, final int id) {
		Transaction transaction = null;
		T result = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			result = c.cast(session.get(c, id));
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return result;
	}
}
