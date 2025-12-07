export interface LoginResponse {
  token: string;
}

export interface UserLogin {
  login: string;
  senha?: string;
}