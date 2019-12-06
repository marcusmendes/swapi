import { Router } from 'express';

const routes = Router();

/* Rota padrão */
routes.get('/', (req, res) => res.json({ message: 'OK' }));

export default routes;
