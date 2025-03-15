/*
 * A simple RDF4J store implementation that maintains a text file for storage.
 *
 * @author  (Andy D)
 * @version (a version number or a date)
 * @since 1.0
 */

package org.example;

import org.eclipse.rdf4j.common.transaction.IsolationLevels;

import org.eclipse.rdf4j.model.Statement;

import org.eclipse.rdf4j.model.Value;

import org.eclipse.rdf4j.sail.SailChangedEvent;

import org.eclipse.rdf4j.sail.SailChangedListener;

import org.eclipse.rdf4j.sail.SailConnectionListener;

import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.io.File;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MM extends MemoryStore {

    MM() throws InterruptedException {

        setDataDir(new File( "MAD.txt"));

        System.out.println(getDataDir().exists());

        getConnection().begin();

        while (getConnection().isActive()) {

            System.out.println("connecting ");


        }


        setDefaultIsolationLevel(IsolationLevels.SNAPSHOT);

        getConnection().commit();

        getConnection().addConnectionListener(new SailConnectionListener() {
            @Override
            public void statementAdded(Statement st) {

                Value value =  st.getObject();

                System.out.println("Statement added: " + st + " with value: " + value);

            }

            @Override
            public void statementRemoved(Statement st) {

                Value value =  st.getObject();

                System.out.println("Statement removed: " + st + " with value: " + value);
            }
        });

        addSailChangedListener(new SailChangedListener() {
            @Override
            public void sailChanged(SailChangedEvent event) {

                System.out.println("Sail changed: " + event);
            }
        });

        System.out.println(isInitialized());

        System.out.println(getConnection().isActive());

        System.out.println(isWritable());

        System.out.println(connectionTimeOut);

        System.out.println(getIterationCacheSyncThreshold());

        ReentrantReadWriteLock lock = initializationLock;

        System.out.println(lock.isWriteLockedByCurrentThread());

        System.out.println(lock.isFair());

        System.out.println(lock.getReadHoldCount());

        System.out.println(lock.getWriteHoldCount());


    }

}
