/**
 * 
 */
package com.brap.authentication.service;

import com.brap.authentication.request.BrapAuthRequest;
import com.brap.authentication.response.AuthResponseView;

/**
 * @author prajwbhat
 *
 */
public interface AuthService {
	AuthResponseView login(BrapAuthRequest authRequest);
}
