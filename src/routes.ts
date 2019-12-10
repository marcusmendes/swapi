import { Router } from 'express';

import DefaultController from './app/Controllers/DefaultController';
import PlanetController from './app/Controllers/PlanetController';

const routes = Router();

/* Rota padrão */
routes.get('/', DefaultController.index);

/* Rota API */
routes.get('/api/planet', PlanetController.index);
routes.post('/api/planet/store', PlanetController.store);

export default routes;
