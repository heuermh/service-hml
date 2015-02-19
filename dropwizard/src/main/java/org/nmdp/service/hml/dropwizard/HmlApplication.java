/*

    hml-dropwizard  HML dropwizard.
    Copyright (c) 2015 National Marrow Donor Program (NMDP)

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 3 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.gnu.org/licenses/lgpl.html

*/
package org.nmdp.service.hml.dropwizard;

import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.databind.SerializationFeature;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.dropwizard.Application;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.nmdp.service.hml.resource.HmlResource;
import org.nmdp.service.hml.resource.HmlServiceModule;

/**
 * HML application.
 */
@Immutable
public final class HmlApplication extends Application<HmlConfiguration> {

    @Override
    public String getName() {
        return "hml";
    }

    @Override
    public void initialize(final Bootstrap<HmlConfiguration> bootstrap) {
        // empty
    }

    @Override
    public void run(final HmlConfiguration configuration, final Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new HmlServiceModule());

        environment.jersey().register(injector.getInstance(HmlResource.class));

        environment.getObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
    }


    /**
     * Main.
     *
     * @param args command line arguments
     * @throws Exception if an error occurs
     */
    public static void main(final String[] args) throws Exception {
        new HmlApplication().run(args);
    }
}
