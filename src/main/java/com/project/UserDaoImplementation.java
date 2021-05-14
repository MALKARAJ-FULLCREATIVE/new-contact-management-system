package com.project;

import java.util.Date;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.repackaged.org.joda.time.DateTime;

public class UserDaoImplementation implements UserDao {

	@Override
	public boolean createUser(User user) {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Filter mailConstraint = new FilterPredicate("email", FilterOperator.EQUAL, user.getEmail());
		Query q = new Query("User").setFilter(mailConstraint);
		Entity entity = datastore.prepare(q).asSingleEntity();
		if (entity != null) {
           return false;
		} else {
			Entity e = new Entity("User", user.getUser_id());
			e.setProperty("email", user.getEmail());
			e.setProperty("password", user.getPassword());
			e.setProperty("user_id", user.getUser_id());
			Date date = user.getCreatedDate();
			DateTime d = new DateTime(date);
			e.setProperty("created", d.getMillis());
			e.setProperty("updated", d.getMillis());

			datastore.put(e);
			return true;
		}

	}

	@Override
	public String getUserId(String email, String password) {

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Filter mailConstraint = new FilterPredicate("email", FilterOperator.EQUAL, email);
		Query q = new Query("User").setFilter(mailConstraint);

		Entity entity = ds.prepare(q).asSingleEntity();

		return entity.getProperty("user_id").toString();

	}

}
