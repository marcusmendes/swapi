import { Router } from 'express';

import DefaultController from './app/Controllers/DefaultController';

const routes = Router();

/* Rota padrão */
routes.get('/', DefaultController.index);

export default routes;
