package com.brahma.utils;

import Model.Admin;
import Model.Client;
import Model.EventCoord;
import Model.Events;
import Model.User;
import java.lang.Class;

public final class Brahma_HibernateUtils {
  public static final Class[] entityAnnotatedClasses = new Class[] {
    Admin.class,
    Client.class,
    EventCoord.class,
    Events.class,
    User.class,
  }
  ;
}
