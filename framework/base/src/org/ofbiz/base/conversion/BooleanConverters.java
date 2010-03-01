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

import java.util.List;
import java.util.Set;

import javolution.util.FastList;
import javolution.util.FastSet;

/** Boolean Converter classes. */
public class BooleanConverters implements ConverterLoader {
    public static class BooleanToInteger extends AbstractConverter<Boolean, Integer> {
        public BooleanToInteger() {
            super(Boolean.class, Integer.class);
        }

        public Integer convert(Boolean obj) throws ConversionException {
             return obj.booleanValue() ? 1 : 0;
        }
    }

    public static class BooleanToList extends AbstractConverter<Boolean, List<Boolean>> {
        public BooleanToList() {
            super(Boolean.class, List.class);
        }

        public List<Boolean> convert(Boolean obj) throws ConversionException {
            List<Boolean> tempList = FastList.newInstance();
            tempList.add(obj);
            return tempList;
        }
    }

    public static class BooleanToSet extends AbstractConverter<Boolean, Set<Boolean>> {
        public BooleanToSet() {
            super(Boolean.class, Set.class);
        }

        public Set<Boolean> convert(Boolean obj) throws ConversionException {
            Set<Boolean> tempSet = FastSet.newInstance();
            tempSet.add(obj);
            return tempSet;
        }
    }

    public static class BooleanToString extends AbstractConverter<Boolean, String> {
        public BooleanToString() {
            super(Boolean.class, String.class);
        }

        public String convert(Boolean obj) throws ConversionException {
            return obj.booleanValue() ? "true" : "false";
        }
    }

    public static class IntegerToBoolean extends AbstractConverter<Integer, Boolean> {
        public IntegerToBoolean() {
            super(Integer.class, Boolean.class);
        }

        public Boolean convert(Integer obj) throws ConversionException {
             return obj.intValue() == 0 ? false : true;
        }
    }

    public static class StringToBoolean extends AbstractConverter<String, Boolean> {
        public StringToBoolean() {
            super(String.class, Boolean.class);
        }

        public Boolean convert(String obj) throws ConversionException {
            return "TRUE".equals(obj.trim().toUpperCase());
        }
    }

    public void loadConverters() {
        Converters.loadContainedConverters(BooleanConverters.class);
    }
}
