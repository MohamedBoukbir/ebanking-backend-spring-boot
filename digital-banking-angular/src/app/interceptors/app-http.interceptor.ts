import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {catchError, throwError} from "rxjs";




export const appHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const accessToken = authService.accessToken;

  if (!req.url.includes("/auth/login")) {
    const newRequest = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${accessToken}`)
    });
    return next(newRequest).pipe(
      catchError(err=>{
        if (err.status==401){
          authService.logout();
        }

        return  throwError(err.message);
      })
    );
  }else{
    return next(req);
  }


};
