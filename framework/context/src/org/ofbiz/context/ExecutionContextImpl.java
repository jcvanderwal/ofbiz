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
package org.ofbiz.context;

import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.ofbiz.base.util.Debug;
import org.ofbiz.entity.AccessController;
import org.ofbiz.entity.DelegatorFactory;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.security.AuthorizationManager;
import org.ofbiz.security.SecurityFactory;
import org.ofbiz.service.GenericDispatcher;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ExecutionContext;

/** An implementation of the <code>ExecutionContext</code> interface. */
public class ExecutionContextImpl extends org.ofbiz.api.context.ExecutionContextImpl implements ExecutionContext {

    public static final String module = ExecutionContextImpl.class.getName();
    protected GenericDelegator delegator = null;
    protected LocalDispatcher dispatcher = null;
    protected AuthorizationManager security = null;
    protected GenericValue userLogin = null;

	public GenericDelegator getDelegator() {
		if (this.delegator == null) {
			this.delegator = DelegatorFactory.getGenericDelegator("default", this);
		}
		return this.delegator;
	}

	public LocalDispatcher getDispatcher() {
		if (this.dispatcher == null) {
	        this.dispatcher = GenericDispatcher.getLocalDispatcher("ExecutionContext", this.getDelegator());
		}
		return this.dispatcher;
	}

	public AuthorizationManager getSecurity() {
		if (this.security == null) {
            try {
				this.security = SecurityFactory.getInstance(this.getDelegator());
			} catch (Exception e) {
			}
		}
		return this.security;
	}

	public GenericValue getUserLogin() {
	    if (this.userLogin == null) {
	        GenericDelegator localDelegator = this.getDelegator();
            try {
                this.userLogin = localDelegator.findOne("UserLogin", false, "userLoginId", "NOT_LOGGED_IN");
            } catch (GenericEntityException e) {
                Debug.logError(e, "Error while getting NOT_LOGGED_IN user: ", module);
            }
            if (this.userLogin == null) {
                this.userLogin = localDelegator.makeValue("UserLogin");
                this.userLogin.set("userLoginId", "NOT_LOGGED_IN");
            }
	    }
		return this.userLogin;
	}

	public void initializeContext(Map<String, ? extends Object> params) {
		this.setDelegator((GenericDelegator) params.get("delegator"));
		this.setDispatcher((LocalDispatcher) params.get("dispatcher"));
		this.setLocale((Locale) params.get("locale")); 
		this.setSecurity((AuthorizationManager) params.get("security"));
		this.setTimeZone((TimeZone) params.get("timeZone"));
		this.setUserLogin((GenericValue) params.get("userLogin"));
	}

	public void setDelegator(GenericDelegator delegator) {
		if (delegator != null) {
			delegator.setExecutionContext(this);
			this.delegator = delegator;
		}
	}

	public void setDispatcher(LocalDispatcher dispatcher) {
		if (dispatcher != null) {
			this.dispatcher = dispatcher;
		}
	}

	public void setSecurity(AuthorizationManager security) {
		if (security != null) {
			this.security = security;
		}
	}

	public void setUserLogin(GenericValue userLogin) {
		if (userLogin != null) {
			this.userLogin = userLogin;
		}
	}

	public AccessController<?> getAccessController() {
        return (AccessController<?>) this.getSecurity().getAccessController(this);
	}
}