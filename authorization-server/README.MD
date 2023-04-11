# Start OAuth2 Procedure

## Get authorization code

```agsl
http://localhost:9000/oauth2/authorize?response_type=code&client_id=taco-admin-client&redirect_uri=http://127.0.0.1:9090/login/oauth2/code/taco-admin-client&scope=writeIngredients+deleteIngredients
```

Redirect url

```url

http://127.0.0.1:9090/login/oauth2/code/taco-admin-client?code=DENwNO_anK6MqW4tPUuEv66Y4UA3VZ_5x04I0UnpL52g0fJR-P4DyzkZqoDqwKJE7ZIksm-Dqfqbr86DlPWekx3RKH654jOZXiVfcbEL_ND1sPhsNTvqscdgm9YSlLrK
```

Authorization code

```bash

DENwNO_anK6MqW4tPUuEv66Y4UA3VZ_5x04I0UnpL52g0fJR-P4DyzkZqoDqwKJE7ZIksm-Dqfqbr86DlPWekx3RKH654jOZXiVfcbEL_ND1sPhsNTvqscdgm9YSlLrK
```

## Get access token

```shell
curl -H"Content-type: application/x-www-form-urlencoded" -d"grant_type=authorization_code" -d"redirect_uri=http://127.0.0.1:9090/login/oauth2/code/taco-admin-client" -d"code=DENwNO_anK6MqW4tPUuEv66Y4UA3VZ_5x04I0UnpL52g0fJR-P4DyzkZqoDqwKJE7ZIksm-Dqfqbr86DlPWekx3RKH654jOZXiVfcbEL_ND1sPhsNTvqscdgm9YSlLrK" -u taco-admin-client:secret localhost:9000/oauth2/token
```

Access token

```code
{
   "access_token":"eyJraWQiOiIzODlkODM4Mi1lZTlhLTQ2YzYtODYwNi1jMGE1MGIwOTE0YTYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdW5kYWxlaSIsImF1ZCI6InRhY28tYWRtaW4tY2xpZW50IiwibmJmIjoxNjgwODczODU1LCJzY29wZSI6WyJkZWxldGVJbmdyZWRpZW50cyIsIndyaXRlSW5ncmVkaWVudHMiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjgwODc0MTU1LCJpYXQiOjE2ODA4NzM4NTV9.d_CItwDYIQqoBNViaLnbBD8O6J2c8anT2gnl35t7RoidzofjHIYNF-thZ_BatAzCDBmZjICDY38o0r3oXfDhcwWDbPe3_pXVOrTZ4zv86qHzSISZk_teT27w9iN8v3M6_UniGlq5RgGQINsHbI3GUH4fxtK2c0NxtihIKSMGvLdSQqPCtA_sZV2DMw-6nco6sgoaJwN7NfQCXg0NGgCVdlicc92tIVc6K3h_Ocd0PHfCfcMmlLBXcqVJX56XNMDDovNXuMV8lf_EsU_PlOWleS4goVYH9QUehHl4msCC1WuOtsk89zu8X3FdXSAfk_SDDDy27lzeUYAWe3e7SUSB6Q",
   "refresh_token":"chn76nWIK2MFAp_IEFmJk6SVd8aN45o02r_JgxuZsthitbCczKuxClTM5V8QZoDzcbyLNxy6KjBfoKCCth-LrfZeCeGVI7GgF4tyhBoBkw28S_ue6Vj7BtHF3KZNdpkm",
   "scope":"deleteIngredients writeIngredients",
   "token_type":"Bearer",
   "expires_in":299
}
```

## Get access token via refresh token

```bash
curl -H"Content-type: application/x-www-form-urlencoded" -d"grant_type=refresh_token&refresh_token=chn76nWIK2MFAp_IEFmJk6SVd8aN45o02r_JgxuZsthitbCczKuxClTM5V8QZoDzcbyLNxy6KjBfoKCCth-LrfZeCeGVI7GgF4tyhBoBkw28S_ue6Vj7BtHF3KZNdpkm" -u taco-admin-client:secret localhost:9000/oauth2/token
```

Access token

```code
{"access_token":"eyJraWQiOiIzODlkODM4Mi1lZTlhLTQ2YzYtODYwNi1jMGE1MGIwOTE0YTYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdW5kYWxlaSIsImF1ZCI6InRhY28tYWRtaW4tY2xpZW50IiwibmJmIjoxNjgwODc1MDc3LCJzY29wZSI6WyJkZWxldGVJbmdyZWRpZW50cyIsIndyaXRlSW5ncmVkaWVudHMiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjgwODc1Mzc3LCJpYXQiOjE2ODA4NzUwNzd9.gGEt9tAjKuX8-BsLG1fyn3VcaRr4AVrY3Sj7vW5LYukws7O0hMWkT-F0bUR_FzlMy4U1WEohgrEb_aZkl-Q2mo7YAgrqofz0kIQBLZnDnsfATGIq_91CSDyA0HTJzGxTra97o9FuQGdLsgrlNQeWHN1PUMF6uv_13nq4dMOQ7_UjGM1llKpqn_RpeHrz1BYzfn7suj8Xyqj1DibsbrpUrTyupds9Cl-rEEhhS-tKO9RkwBoveVmGRXvLFrsIRG5mzsLgDgfusSxe8owwaKq0mtGzbUgblPlak9SnZJNjvufx-zGJBe7_5Ql09vUxAxjaQJumll8BedDFR5ptBym5mA","refresh_token":"chn76nWIK2MFAp_IEFmJk6SVd8aN45o02r_JgxuZsthitbCczKuxClTM5V8QZoDzcbyLNxy6KjBfoKCCth-LrfZeCeGVI7GgF4tyhBoBkw28S_ue6Vj7BtHF3KZNdpkm","scope":"deleteIngredients writeIngredients","token_type":"Bearer","expires_in":299}
```
