export interface LoginResponse {
  token: string;
}

export interface UserLogin {
  login: string;
  senha?: string; // Opcional pq as vezes não enviamos a senha de volta
}