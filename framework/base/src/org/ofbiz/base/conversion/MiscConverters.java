/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.ofbiz.base.conversion;

import java.util.Locale;

import org.ofbiz.base.util.UtilMisc;

/** Miscellaneous Converter classes. */
public class MiscConverters {

    public static class LocaleToString extends AbstractConverter<Locale, String> {

        public String convert(Locale obj) throws ConversionException {
             return obj.toString();
        }

        public Class<Locale> getSourceClass() {
            return Locale.class;
        }

        public Class<String> getTargetClass() {
            return String.class;
        }

    }

    public static class StringToLocale extends AbstractConverter<String, Locale> {

        public Locale convert(String obj) throws ConversionException {
            Locale loc = UtilMisc.parseLocale(obj);
            if (loc != null) {
                return loc;
            } else {
                throw new ConversionException("Could not convert " + obj + " to Locale: ");
            }
        }

        public Class<String> getSourceClass() {
            return String.class;
        }

        public Class<Locale> getTargetClass() {
            return Locale.class;
        }

    }

}