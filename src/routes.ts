import { Router } from 'express';
import swaggerUI from 'swagger-ui-express';
import { resolve } from 'path';
import YAML from 'yamljs';

import DefaultController from './app/Controllers/DefaultController';
import PlanetController from './app/Controllers/PlanetController';

const routes = Router();
const swaggerDocument = YAML.load(resolve('src', 'apidoc', 'swagger.yaml'));

/* Rota padrão */
routes.get('/', DefaultController.index);

/** apidoc */
routes.use('/apidoc', swaggerUI.serve);
routes.get('/apidoc', swaggerUI.setup(swaggerDocument));

/* Rota API */
routes.get('/api', DefaultController.index);
routes.get('/api/planet', PlanetController.index);
routes.post('/api/planet/store', PlanetController.store);
routes.get('/api/planet/:idPlanet', PlanetController.show);
routes.delete('/api/planet/:idPlanet', PlanetController.destroy);

export default routes;
